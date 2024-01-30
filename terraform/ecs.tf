resource "aws_ecs_cluster" "ecs-cluster" {
  name = "${var.app_name}"
  
  setting {
    name  = "containerInsights"
    value = "enabled"
  }
}

resource "aws_ecs_cluster_capacity_providers" "ecs-cluster-capacity" {
  cluster_name = aws_ecs_cluster.ecs-cluster.name
  capacity_providers = ["FARGATE"]

  default_capacity_provider_strategy {
    base              = 1
    weight            = 100
    capacity_provider = "FARGATE"
  }
}

resource "aws_ecr_repository" "ecr-repository" {
  name                 = "${var.app_name}"
  image_tag_mutability = "MUTABLE"

  image_scanning_configuration {
    scan_on_push = true
  }
}

data "aws_iam_policy_document" "ecr-repository-policy" {
  statement {
    sid    = "${var.app_name}_repository_policy"
    effect = "Allow"

    principals {
      type        = "*"
      identifiers = ["arn:aws:iam::137703291354:user/builder"]
    }

    actions = [
      "ecr:GetDownloadUrlForLayer",
      "ecr:BatchGetImage",
      "ecr:BatchCheckLayerAvailability",
      "ecr:PutImage",
      "ecr:InitiateLayerUpload",
      "ecr:UploadLayerPart",
      "ecr:CompleteLayerUpload",
      "ecr:DescribeRepositories",
      "ecr:GetRepositoryPolicy",
      "ecr:ListImages",
      "ecr:DeleteRepository",
      "ecr:BatchDeleteImage",
      "ecr:SetRepositoryPolicy",
      "ecr:DeleteRepositoryPolicy",
    ]
  }
}

resource "aws_ecr_repository_policy" "attach-ecr-policy" {
  repository = aws_ecr_repository.ecr-repository.name
  policy     = data.aws_iam_policy_document.ecr-repository-policy.json
}
