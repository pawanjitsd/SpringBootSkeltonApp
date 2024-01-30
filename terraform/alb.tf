resource "aws_lb" "ecs-alb" {
  name               = "${var.app_name}-alb"
  internal           = true
  load_balancer_type = "application"
  security_groups    = "${var.security_groups}"
  subnets            = "${var.subnets}"

  enable_deletion_protection = false
  tags = {  
	Environment = "development"
  }
}


resource "aws_lb_target_group" "ecs-tg" {
  name        = "${var.app_name}-tg"
  port        = 80
  protocol    = "HTTP"
  vpc_id      = "${var.vpc_id}"
  target_type = "ip"
  health_check {
   path = "/actuator/health"
   protocol = "HTTP"
   matcher = "200"
   healthy_threshold = 2
   unhealthy_threshold = 2
   timeout = 5
   interval = 15
	
  }
}

resource "aws_lb_listener" "ecs-alb-listener" {
  load_balancer_arn = aws_lb.ecs-alb.arn
  port              = "80"
  protocol          = "HTTP"

  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.ecs-tg.arn
  }
}
