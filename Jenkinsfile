pipeline {
  agent any 
    environment {
        uid = "${env.JOB_NAME}-${currentBuild.id}"
    }
    parameters {
        string(
            name: 'app_name',
            defaultValue: 'dubbo-service',
            description: 'dubbo-service'
            )
        string(
            name: 'image_name',
            defaultValue: 'infra-xyzhao/dubbo-demo-service',
            description: 'infra-xyzhao/dubbo-demo-service:latest'
            )
        string(
            name: 'git_repo',
            defaultValue: 'git@github.com:zhaoxy8/jenkins-code-pipline.git',
            description: 'git@github.com:zhaoxy8/jenkins-code-pipline.git'
            )
        string(
            name: 'git_ver',
            defaultValue: 'master',
            description: 'master :commit id'
            )
        string(
            name: 'add_tag',
            defaultValue: '20200504_1856',
            description: 'example: $git_ver_$add_tag=master_20200504_1830'
            )
        string(
            name: 'mvn_dir',
            defaultValue: './',
            description: 'default folder'
            )
        string(
            name: 'target_dir',
            defaultValue: './dubbo-server/target',
            description: 'example: ./dubbo-server/target'
            )
        string(
            name: 'mvn_cmd',
            defaultValue: 'mvn clean package -Dmaven.test.skip=true',
            description: '-e -q ingore stdout,only stderr'
            )
        choice(
            name: 'base_image',
            choices: 'infra-xyzhao/jre8:8u251',
            description: 'base_image'
            )    
        choice(
            name: 'maven',
            choices: 'maven-3.6.3-8u251',
            description: 'maven-3.6.3-8u251'
            )   
    }
    stages {
      stage('pull') { //get project code from repo 
        steps {
          sh "git clone ${params.git_repo} ${params.app_name}/${env.BUILD_NUMBER} && cd ${params.app_name}/${env.BUILD_NUMBER} && git checkout ${params.git_ver}"
        }
      }
      stage('build') { //exec mvn cmd
        steps {
          sh "cd ${params.app_name}/${env.BUILD_NUMBER}/dubbo-demo-service &&/var/jenkins_home/${params.maven}/bin/${params.mvn_cmd}"
        }
      }
      stage('package') { //move jar file into project_dir
        steps {
          sh "cd ${params.app_name}/${env.BUILD_NUMBER}/dubbo-demo-service && cd ${params.target_dir} && mkdir project_dir && mv *.jar ./project_dir"
        }
      }
      stage('image') { //build image and push to registry
        steps {
          writeFile file: "${params.app_name}/${env.BUILD_NUMBER}/dubbo-demo-service/Dockerfile", text: """FROM registry.cn-hangzhou.aliyuncs.com/${params.base_image}
 USER root
 ADD ${params.target_dir}/project_dir /opt/project_dir"""
          sh "cat ${params.app_name}/${env.BUILD_NUMBER}/dubbo-demo-service/Dockerfile && \
          docker pull registry.cn-hangzhou.aliyuncs.com/${params.base_image} && \
          cd  ${params.app_name}/${env.BUILD_NUMBER}/dubbo-demo-service && \
          docker build -t registry.cn-hangzhou.aliyuncs.com/${params.image_name}:${params.git_ver}_${params.add_tag} . && \
          docker push registry.cn-hangzhou.aliyuncs.com/${params.image_name}:${params.git_ver}_${params.add_tag}"
        }
      }
    }
}
