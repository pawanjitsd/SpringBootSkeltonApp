# Account id will be overriden by terraform.tfvars
variable "aws_account_id" {type=string}
variable "aws_region" {type=string}
variable "app_name" {type=string}
variable "app_environment" {type=string}
variable "image_name" {type=string}
variable "image_tag" {type=string}
variable "vpc_id"   {type=string}
variable "subnets" {type=list(string)}
variable "security_groups" {type=list(string)} 
