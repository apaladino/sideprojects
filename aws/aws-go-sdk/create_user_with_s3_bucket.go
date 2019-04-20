package main

import (
	"./util"
	"github.com/aws/aws-sdk-go/aws"
	"github.com/aws/aws-sdk-go/aws/credentials"
	"github.com/aws/aws-sdk-go/aws/session"
	"github.com/aws/aws-sdk-go/service/iam"
	"github.com/aws/aws-sdk-go/service/s3"
	"github.com/jessevdk/go-flags"
	"log"
	"os"
	"strconv"
)


var opts struct {
	AwsProfile string `long:"aws_access_key" default:"andy" env:"AWS_PROFILE" description:"AWS IAM Profile ID"`
	AwsRegion string `long:"aws_region" env:"AWS_REGION" default:"us-west-2" description:"AWS Region to target"`
	S3UsersBucket string `long:"s3_users_bucket" default:"users" description:"Default S3 bucket for user data"`
}

func main() {

	log.Println("Running create_user_with_s3_bucket script")

	var userName = os.Args[1]
	var cleanUp = false
	if len(os.Args) > 2 {
		cleanUp, _ = strconv.ParseBool(os.Args[2])
	}

	//parse command-line args
	_, err := flags.Parse(&opts)
	if err != nil {
		log.Fatal(err.Error())
	}

	//sess := session.Must(session.NewSession(&aws.Config{HTTPClient: &http.Client{Timeout: 30 * time.Second}}))
	sess, err := session.NewSession(&aws.Config{
		Region:      aws.String(opts.AwsRegion),
		Credentials: credentials.NewSharedCredentials("", opts.AwsProfile),
	})

	iamSvc := iam.New(sess)
	s3Svc := s3.New(sess)

	// Generate new IamUtil object
	iamUtil, err := util.NewIamUtilImpl(iamSvc, opts.AwsProfile, opts.AwsRegion)
	if err != nil{
		log.Fatal("Unable to instantiate IamUtil object", err)
	}

	// Generate new S3Util object
	s3Util, err := util.NewS3UtilImpl(s3Svc, opts.AwsProfile, opts.AwsRegion)
	if err != nil{
		log.Fatal("Unable to instantiate IamUtil object", err)
	}

	// Create new AWS User
	newUser, err := iamUtil.CreateNewUser(userName)
	if err != nil || newUser == nil{
		log.Fatal("Unable to create new user.", err)
	}

	// Generate new AWS Access Key
	awsAccessKey, err := iamUtil.GenerateAccessKey(userName)
	if err != nil {
		log.Fatal("Unable to generate access key for user", err)
	}
	log.Println("Access Key Generated: ", awsAccessKey)

	// Create a new S3 bucket for user
	const bucketPrefix = "user_buckets"
	bucket, err := s3Util.CreateS3BucketForUser(userName)
	if err != nil{
		log.Fatalf("Unable to create user bucket: %s/%s", bucketPrefix, userName)
	}
	log.Printf("New S3 Bucket created: %s/%s", bucketPrefix, userName, bucket)

	// Add a bucket policy for new S3 bucket granting all S3 actions
	bucketPolicy, err := s3Util.CreateDefaultBucketPolicy(bucket)
	if err != nil{
		log.Fatalf("Unable to create S3 bucket access policy: %v", err)
	}
	log.Printf("New S3 Bucket Access Policy created: %v", bucketPolicy)

	// clean up
	if cleanUp {

	}
	s3Util.DeleteS3BucketPolicy(*bucket.Location)
	s3Util.DeleteS3Bucket(bucketPrefix, userName)
	iamUtil.DeleteAccessKey(awsAccessKey.AccessKey.AccessKeyId, userName)
	iamUtil.DeleteUser(userName)
}