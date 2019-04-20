package util

import (
	"fmt"
	"github.com/aws/aws-sdk-go/aws"
	"github.com/aws/aws-sdk-go/aws/awserr"
	"github.com/aws/aws-sdk-go/service/iam"
	"log"
	"os"
)

//IamUtilImpl utility object for accessing AWS IAM Service
type IamUtilImpl struct {
	iamSvc *iam.IAM
	awsProfile string
	awsRegion string
}


func NewIamUtilImpl(
	iamSvc *iam.IAM,
	awsProfile string,
	awsRegion string,
) (*IamUtilImpl, error) {
	return &IamUtilImpl{
		iamSvc: iamSvc,
		awsProfile: awsProfile,
		awsRegion: awsRegion,
	}, nil
}

//CreateNewUser - creates a new user in AWS
func(iamUtil *IamUtilImpl) CreateNewUser(userName string) (*iam.CreateUserOutput, error) {
	log.Printf("Adding new user: %s\n", userName)
	// Look up user and create new user if they do not already exist
	_, err := iamUtil.iamSvc.GetUser(&iam.GetUserInput{
		UserName: &userName,
	})

	if awserr, ok := err.(awserr.Error); ok && awserr.Code() == iam.ErrCodeNoSuchEntityException {
		newUser, err := iamUtil.iamSvc.CreateUser(&iam.CreateUserInput{
			UserName: &os.Args[1],
		})

		if err != nil {
			log.Println("Unable to create new user", err)
			return newUser, err
		}

		log.Printf("User %s successfully created", userName, newUser)
		return newUser, nil
	}

	return nil, fmt.Errorf("User %s already exists in AWS account. Exiting.", userName)
}

//GenerateAccessKey generates a new AWS accesskey for user
func(iamUtil *IamUtilImpl) GenerateAccessKey(userName string) (*iam.CreateAccessKeyOutput, error) {
	log.Printf("Generating access key for userName: %s", userName)

	// Generate AWS access_key for new user
	result, err := iamUtil.iamSvc.CreateAccessKey(&iam.CreateAccessKeyInput{
		UserName: aws.String(userName),
	})

	return result, err
}

//DeleteUser - deletes user in AWS
func(iamUtil *IamUtilImpl) DeleteUser(userName string) error{
	_, err := iamUtil.iamSvc.DeleteUser(&iam.DeleteUserInput{
		UserName: &userName,
	})

	if err != nil {
		log.Printf("Unable to delete user: %s\n", userName)
	}
	return err
}

func (iamUtil *IamUtilImpl) DeleteAccessKey(AccessKeyId *string, userName string) error {

	_, err := iamUtil.iamSvc.DeleteAccessKey(&iam.DeleteAccessKeyInput{
		AccessKeyId: aws.String("ACCESS_KEY_ID"),
		UserName:    aws.String("USER_NAME"),
	})

	if err != nil {
		fmt.Println("Unable to delete acceskey for user", err)
		return err
	}
	return nil
}