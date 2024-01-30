resource "aws_ecs_service" "springbootskeltonapp" {
  name            = "${var.app_name}"
  cluster         = aws_ecs_cluster.ecs-cluster.id 
  task_definition =  aws_ecs_task_definition.ecs-task.id
  desired_count   = 2

  load_balancer {
    target_group_arn = aws_lb_target_group.ecs-tg.arn 
    container_name   = "${var.app_name}"
    container_port   = 8080
  }
  network_configuration {
   subnets = "${var.subnets}"
   security_groups = "${var.security_groups}"
   assign_public_ip = true	
  }
  deployment_circuit_breaker {
    enable = true
    rollback = true
  }
  
}


