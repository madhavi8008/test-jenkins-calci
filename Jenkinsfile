pipeline {
    agent any
    environment {
        REGISTRY = 'your-docker-registry'
        IMAGE_NAME = 'calci'
        CHART_NAME = 'calci-chart'
        K8S_CLUSTER = 'your-k8s-cluster'
        KUBECONFIG_PATH = '/path/to/kubeconfig'  // Use this if you need a specific Kubeconfig file
    }
    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }
        stage('Build and Test') {
            steps {
                sh './gradlew clean build'
            }
        }
        stage('Build Docker Image with Jib') {
            steps {
                sh './gradlew jib --image=$REGISTRY/$IMAGE_NAME:latest'
            }
        }
        stage('Push Docker Image to Registry') {
            steps {
                withDockerRegistry([credentialsId: 'docker-credentials', url: 'https://index.docker.io/v1/']) {
                    sh './gradlew jib --image=$REGISTRY/$IMAGE_NAME:latest'
                }
            }
        }
        stage('Package Helm Chart') {
            steps {
                script {
                    // Package the Helm chart
                    sh "helm package ./calci-chart --destination ./charts"
                }
            }
        }
        stage('Deploy to Kubernetes') {
            steps {
                script {
                    // Configure kubectl to use the correct Kubeconfig (if needed)
                    sh "export KUBEV2=$KUBECONFIG_PATH"

                    // Deploy the application using Helm
                    sh "helm upgrade --install calci ./calci-chart --namespace default --set image.repository=$REGISTRY/$IMAGE_NAME --set image.tag=latest --set ingress.enabled=true --set ingress.host=calci.local"
                }
            }
        }
    }
    post {
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
