resource "aws_cloudwatch_log_group" "ecr_log_group" {
  name              = "${var.app_name}"
  retention_in_days = 7
  lifecycle {
    prevent_destroy = false
  }
}

resource "aws_iam_role" "ecr-execution-role" {
  name = "${var.app_name}-ecr-execution-role"
  assume_role_policy = jsonencode({
   "Version": "2012-10-17",
   "Statement": [
        {
            "Sid": "",
            "Effect": "Allow",
            "Principal": {
                "Service": "ecs-tasks.amazonaws.com"
            },
            "Action": "sts:AssumeRole"
        }
    ]
 })
}

resource "aws_iam_role" "ecr-task-role" {
  name = "${var.app_name}-ecr-task-role"
  assume_role_policy = jsonencode({
   "Version": "2012-10-17",
   "Statement": [
        {
            "Sid": "",
            "Effect": "Allow",
            "Principal": {
                "Service": "ecs-tasks.amazonaws.com"
            },
            "Action": "sts:AssumeRole"
        }
    ]
 }) 
}

resource "aws_iam_policy" "ecr-execution-role_policy" {
  name = "${var.app_name}-ecr-execution-role-policy"
  policy = jsonencode({
    "Version": "2012-10-17",
    "Statement": [
        {
            "Sid": "",
            "Effect": "Allow",
            "Action": [
                "logs:PutLogEvents",
                "logs:CreateLogStream",
		"logs:CreateLogGroup"

            ],
            "Resource": [
                "${aws_cloudwatch_log_group.ecr_log_group.arn}:*"
            ]
        },
     	{
            "Sid": "",
            "Effect": "Allow",
            "Action": [
                "ecr:GetAuthorizationToken",
                "ecr:BatchCheckLayerAvailability",
                "ecr:GetDownloadUrlForLayer",
                "ecr:BatchGetImage"
            ],
            "Resource": [
               "*"
            ]   
        },
	{
            "Sid": "",
            "Effect": "Allow",
            "Action": [
        //      "secretsmanager:GetSecretValue",
       //   	"kms:Decrypt",
        	"s3:GetObject",
                "s3:GetBucketLocation"
	    ],
            "Resource": [
               "*"
            ]
        }
    ]
   })
   depends_on = [aws_ecr_repository.ecr-repository]
}


# Attach the Lambda policy to previously created role
resource "aws_iam_role_policy_attachment" "policy-attach-to-ecr-execution-role" {
  policy_arn = aws_iam_policy.ecr-execution-role_policy.arn
  role       = aws_iam_role.ecr-execution-role.id
}


resource "aws_iam_policy" "ecr-task-role-policy" {
  name = "springbootskeltonapp-ecr-task-role_policy"
  policy = jsonencode({
    "Version" : "2012-10-17",
    "Statement": [
        {
            "Sid": "",
            "Effect": "Allow",
            "Action": [
                "logs:PutLogEvents",
                "logs:CreateLogStream",
                "logs:CreateLogGroup"
 
            ],
            "Resource": [
		"${aws_cloudwatch_log_group.ecr_log_group.arn}:*"
            ]
        },
        {
            "Sid": "",
            "Effect": "Allow",
            "Action": [
                "ssmmessages:OpenDataChannel",
                "ssmmessages:OpenControlChannel",
                "ssmmessages:CreateDataChannel",
                "ssmmessages:CreateControlChannel"
            ],
            "Resource": "*"
        },
	{
            "Sid": "",
            "Effect": "Allow",
            "Action": [
               "secretsmanager:GetSecretValue",
	 	"s3:GetObject",
		"s3:GetBucketLocation" 
            ],  
            "Resource": [
                "*"
            ]
        }
    ]
     })
}


# Attach the Lambda policy to previously created role
resource "aws_iam_role_policy_attachment" "policy_attach_to_ecr-task-role" {
  policy_arn = aws_iam_policy.ecr-task-role-policy.arn
  role       = aws_iam_role.ecr-task-role.id
}
        
