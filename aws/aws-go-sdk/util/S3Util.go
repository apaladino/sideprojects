package util

import (
	"encoding/json"
	"fmt"
	"github.com/aws/aws-sdk-go/aws"
	"github.com/aws/aws-sdk-go/aws/awserr"
	"github.com/aws/aws-sdk-go/aws/session"
	"github.com/aws/aws-sdk-go/service/s3"
	"log"
)

//IamUtilImpl utility object for accessing AWS IAM Service
type S3UtilImpl struct {
	s3Svc *s3.S3
	awsProfile string
	awsRegion string
}

//NewS3UtilImpl - constructor
func NewS3UtilImpl(
	s3Svc *s3.S3,
	awsProfile string,
	awsRegion string,
) (*S3UtilImpl, error) {
	return &S3UtilImpl{
		s3Svc: s3Svc,
		awsProfile: awsProfile,
		awsRegion: awsRegion,
	}, nil
}

//CreateS3BucketForUser - creates new S3 Bucket
func (s3Util S3UtilImpl) CreateS3BucketForUser(bucket string) (*s3.CreateBucketOutput, error) {
	input := &s3.CreateBucketInput{
		Bucket: aws.String(bucket),
		CreateBucketConfiguration: &s3.CreateBucketConfiguration{
			LocationConstraint: aws.String(s3Util.awsRegion),
		},
	}

	result, err := s3Util.s3Svc.CreateBucket(input)
	if err != nil {
		if aerr, ok := err.(awserr.Error); ok {
			switch aerr.Code() {
			case s3.ErrCodeBucketAlreadyExists:
				log.Println(s3.ErrCodeBucketAlreadyExists, aerr.Error())
				return result, nil
			case s3.ErrCodeBucketAlreadyOwnedByYou:
				log.Println(s3.ErrCodeBucketAlreadyOwnedByYou, aerr.Error())
				return result, nil
			default:
				log.Println(aerr.Error())
			}
		} else {
			log.Println(err.Error())
		}
		return nil, err
	}

	err = s3Util.s3Svc.WaitUntilBucketExists(&s3.HeadBucketInput{
		Bucket: aws.String(bucket),
	})
	if err != nil {
		return nil, fmt.Errorf("Error occurred while waiting for bucket to be created, %v", bucket)
	}

	return result, err
}

func (s3Util S3UtilImpl) DeleteS3Bucket(bucketPrefix string, folder string) error {
	var bucketName = fmt.Sprintf("%s/%s", bucketPrefix, folder)
	svc := s3.New(session.New())
	input := &s3.DeleteBucketInput{
		Bucket: aws.String(bucketName),
	}

	_, err := svc.DeleteBucket(input)

	return err
}

func (s3Util S3UtilImpl) CreateDefaultBucketPolicy(bucket *s3.CreateBucketOutput) (*s3.PutBucketPolicyOutput, error) {
	log.Printf("Creating S3 bucket policy for bucket: %s\n", bucket.Location)
	// Create a generate policy allowing all s3 actions
	allAccessPolicy := map[string]interface{}{
		"Version": "2019-04-17",
		"Statement": []map[string]interface{}{
			{
				"Sid":       "AddPerm",
				"Effect":    "Allow",
				"Principal": "*",
				"Action": []string{
					"s3:*",
				},
				"Resource": []string{
					fmt.Sprintf("arn:aws:s3:::%s/*", bucket),
				},
			},
		},
	}

	// Marshal the policy into a JSON value so that it can be sent to S3.
	policyStr, err := json.Marshal(allAccessPolicy)
	if err != nil {
		return nil, fmt.Errorf("Failed to marshal policy, %v", err)
	}

	// Call S3 to put the policy for the bucket.
	bucketPolicy, err := s3Util.s3Svc.PutBucketPolicy(&s3.PutBucketPolicyInput{
		Bucket: aws.String(*bucket.Location),
		Policy: aws.String(string(policyStr)),
	})
	if err != nil {
		if aerr, ok := err.(awserr.Error); ok && aerr.Code() == s3.ErrCodeNoSuchBucket {
			// Special error handling for the when the bucket doesn't
			// exists so we can give a more direct error message from the CLI.
			return bucketPolicy, fmt.Errorf("Unable to create bucket. Bucket %q does not exist", bucket)
		}
		return bucketPolicy, fmt.Errorf("Unable to create bucket %q policy, %v", bucket, err)
	}

	fmt.Printf("Successfully set bucket %q's policy\n", bucket)
	return bucketPolicy, nil
}

func (s3Util S3UtilImpl) DeleteS3BucketPolicy(bucket string) error {
	log.Printf("Deleting bucket policy: %v", bucket)


	// Call S3 to delete the policy on the bucket.
	_, err := s3Util.s3Svc.DeleteBucketPolicy(&s3.DeleteBucketPolicyInput{
		Bucket: aws.String(bucket),
	})
	if err != nil {
		if aerr, ok := err.(awserr.Error); ok && aerr.Code() == s3.ErrCodeNoSuchBucket {
			// Special error handling for the when the bucket doesn't
			// exists so we can give a more direct error message from the CLI.
			return fmt.Errorf("Bucket %q does not exist", bucket)
		}
		return fmt.Errorf("Unable to delete bucket %q policy, %v", bucket, err)
	}

	fmt.Printf("Successfully deleted the policy on bucket %q.\n", bucket)
	return nil
}
