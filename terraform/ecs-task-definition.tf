resource "aws_ecs_task_definition" "ecs-task" {
  family = "${var.app_name}"
  requires_compatibilities = ["FARGATE"]
  network_mode             = "awsvpc"
  cpu                      = 1024
  memory                   = 2048
  execution_role_arn = aws_iam_role.ecr-execution-role.arn 
  task_role_arn = aws_iam_role.ecr-task-role.arn
  depends_on = [aws_iam_role.ecr-execution-role,aws_iam_role.ecr-task-role]
  container_definitions = jsonencode([
    {
      name      = "${var.app_name}"
      image     = "${var.aws_account_id}.dkr.ecr.${var.aws_region}.amazonaws.com/${var.image_name}:${var.image_tag}"
      cpu       = 1024
      memory    = 2048
      essential = true
      user 	= "1000",
      environmentFiles: [
		{
                    "value": "arn:aws:s3:::psingh-app-configs/springbootskeltonapp/environment.env",
                    "type": "s3"
                }
      ],
      portMappings = [
        {
          containerPort = 8080
          hostPort      = 8080
        }
      ],
      "healthCheck": {
        "command": [ "CMD-SHELL", "curl -f http://localhost:8080/actuator/health || exit 1" ],
        "startPeriod": 40
      },
      "logConfiguration": {
                "logDriver": "awslogs",
                "options": {
                    "awslogs-group": "${var.app_name}",
                    "awslogs-region": "us-west-2",
                    "awslogs-stream-prefix": "container"
                }
       }
    }
  ])
}
