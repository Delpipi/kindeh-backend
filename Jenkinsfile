pipeline {
    agent any

    environment {
        registry = "alexandrepaulkouame"
        registryCredential = "dockerhub"
    }

    stages {

        stage('BUILD and TEST MICROSERVICES') {

            parallel {

                stage('Build Discovery Service') {
                    steps {
                        script {
                            dir('discovery-service') {
                                // Build the discovery-service project using Maven
                                sh 'mvn clean install -DskipTests'
                                // Integration Test
                                sh 'mvn verify -DskipUnitTests'
                                // Code Analysis
                                sh 'mvn checkstyle:checkstyle'
                            }
                        }
                    }
                    post {
                        success {
                            echo 'Now Archiving Discovery Service artifacts...'
                            archiveArtifacts artifacts: 'discovery-service/target/*.jar'
                        }
                    }
                }

                stage('Build Config Service') {
                    steps {
                        script {
                            dir('config-service') {
                                // Build the config-service project using Maven
                                sh 'mvn clean install -DskipTests'
                                // Integration Test
                                sh 'mvn verify -DskipUnitTests'
                                // Code Analysis
                                sh 'mvn checkstyle:checkstyle'
                            }
                        }
                    }
                    post {
                        success {
                            echo 'Now Archiving Config Service artifacts...'
                            archiveArtifacts artifacts: 'config-service/target/*.jar'
                        }
                    }
                }

                stage('Build Customer Service') {
                    steps {
                        script {
                            dir('customer-service') {
                                // Build the customer-service project using Maven
                                sh 'mvn clean install -DskipTests'
                                // Integration Test
                                sh 'mvn verify -DskipUnitTests'
                                // Code Analysis
                                sh 'mvn checkstyle:checkstyle'
                            }
                        }
                    }
                    post {
                        success {
                            echo 'Now Archiving Customer Service artifacts...'
                            archiveArtifacts artifacts: 'customer-service/target/*.jar'
                        }
                    }
                }

                stage('Build Gateway Service') {
                    steps {
                        script {
                            dir('gateway-service') {
                                // Build the gateway-service project using Maven
                                sh 'mvn clean install -DskipTests'
                                // Integration Test
                                sh 'mvn verify -DskipUnitTests'
                                // Code Analysis
                                sh 'mvn checkstyle:checkstyle'
                            }
                        }
                    }
                    post {
                        success {
                            echo 'Now Archiving Gateway Service artifacts...'
                            archiveArtifacts artifacts: 'gateway-service/target/*.jar'
                        }
                    }
                }
            }
        }

        stage('CODE ANALYSIS WITH SONARQUBE') {

            environment {
                 scannerHome = tool 'SONAR6.2'
            }

            parallel {

                stage('Sonar Code Analysis of Discovery Service') {
                    steps {
                        dir('discovery-service') {
                              withSonarQubeEnv('sonar-pro') {
                                 sh '''${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=discovery-service \
                                    -Dsonar.projectName=discovery-service-repo \
                                    -Dsonar.projectVersion=1.0 \
                                    -Dsonar.sources=src/ \
                                    -Dsonar.java.binaries=target/test-classes/com/visualpathit/account/controllerTest/ \
                                    -Dsonar.junit.reportsPath=target/surefire-reports/ \
                                    -Dsonar.jacoco.reportsPath=target/jacoco.exec \
                                    -Dsonar.java.checkstyle.reportPaths=target/checkstyle-result.xml'''
                             }

                             timeout(time: 10, unit: 'MINUTES') {
                                 waitForQualityGate abortPipeline: true
                             }
                        }
                    }
                }

                stage('Sonar Code Analysis of Config Service') {
                    steps {
                        dir('config-service') {
                              withSonarQubeEnv('sonar-pro') {
                                 sh '''${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=config-service \
                                    -Dsonar.projectName=config-service-repo \
                                    -Dsonar.projectVersion=1.0 \
                                    -Dsonar.sources=src/ \
                                    -Dsonar.java.binaries=target/test-classes/com/visualpathit/account/controllerTest/ \
                                    -Dsonar.junit.reportsPath=target/surefire-reports/ \
                                    -Dsonar.jacoco.reportsPath=target/jacoco.exec \
                                    -Dsonar.java.checkstyle.reportPaths=target/checkstyle-result.xml'''
                             }

                             timeout(time: 10, unit: 'MINUTES') {
                                 waitForQualityGate abortPipeline: true
                             }
                        }
                    }
                }

                stage('Sonar Code Analysis of Customer Service') {
                    steps {
                        dir('customer-service') {
                              withSonarQubeEnv('sonar-pro') {
                                 sh '''${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=customer-service \
                                    -Dsonar.projectName=customer-service-repo \
                                    -Dsonar.projectVersion=1.0 \
                                    -Dsonar.sources=src/ \
                                    -Dsonar.java.binaries=target/test-classes/com/visualpathit/account/controllerTest/ \
                                    -Dsonar.junit.reportsPath=target/surefire-reports/ \
                                    -Dsonar.jacoco.reportsPath=target/jacoco.exec \
                                    -Dsonar.java.checkstyle.reportPaths=target/checkstyle-result.xml'''
                             }

                             timeout(time: 10, unit: 'MINUTES') {
                                 waitForQualityGate abortPipeline: true
                             }
                        }
                    }
                }

                stage('Sonar Code Analysis of Gateway Service') {
                    steps {
                         dir('gateway-service') {
                              withSonarQubeEnv('sonar-pro') {
                                 sh '''${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=gateway-service \
                                    -Dsonar.projectName=gateway-service-repo \
                                    -Dsonar.projectVersion=1.0 \
                                    -Dsonar.sources=src/ \
                                    -Dsonar.java.binaries=target/test-classes/com/visualpathit/account/controllerTest/ \
                                    -Dsonar.junit.reportsPath=target/surefire-reports/ \
                                    -Dsonar.jacoco.reportsPath=target/jacoco.exec \
                                    -Dsonar.java.checkstyle.reportPaths=target/checkstyle-result.xml'''
                             }

                             timeout(time: 10, unit: 'MINUTES') {
                                 waitForQualityGate abortPipeline: true
                             }
                         }
                    }
                }
            }

        }

        stage('BUILD Docker IMAGES') {

            parallel {

                stage('Build Docker Image for Config Service') {
                    steps {
                        script {
                            dir('config-service') {
                                docker.build("${registry}/kindeh-config-service:V$BUILD_NUMBER")
                            }
                        }
                    }
                }

                stage('Build Docker Image for Discovery Service') {
                    steps {
                        script {
                            dir('discovery-service') {
                                docker.build("${registry}/kindeh-discovery-service:V$BUILD_NUMBER")
                            }
                        }
                    }
                }

                stage('Build Docker Image for Customer Service') {
                    steps {
                        script {
                            dir('customer-service') {
                                docker.build("${registry}/kindeh-customer-service:V$BUILD_NUMBER")
                            }
                        }
                    }
                }

                stage('Build Docker Image for Gateway Service') {
                    steps {
                        script {
                            dir('gateway-service') {
                                docker.build("${registry}/kindeh-gateway-service:V$BUILD_NUMBER")
                            }
                        }
                    }
                }

            }
        }

        stage('Upload IMAGES Customer DOCKER HUB') {
            steps {
                script {
                    docker.withRegistry('', registryCredential) {
                        docker.push("${registry}/kindeh-config-service:V$BUILD_NUMBER")
                        docker.push("${registry}/kindeh-config-service:latest")
                        docker.push("${registry}/kindeh-customer-service:V$BUILD_NUMBER")
                        docker.push("${registry}/kindeh-customer-service:latest")
                        docker.push("${registry}/kindeh-discovery-service:V$BUILD_NUMBER")
                        docker.push("${registry}/kindeh-discovery-service:latest")
                        docker.push("${registry}/kindeh-gateway-service:V$BUILD_NUMBER")
                        docker.push("${registry}/kindeh-gateway-service:latest")
                    }
                }
            }
        }

        stage('Remove UNUSED DOCKER IMAGE') {
            steps {
                sh "docker rmi ${registry}/kindeh-config-service:V$BUILD_NUMBER"
                sh "docker rmi ${registry}/kindeh-customer-service:V$BUILD_NUMBER"
                sh "docker rmi ${registry}/kindeh-discovery-service:V$BUILD_NUMBER"
                sh "docker rmi ${registry}/kindeh-gateway-service:V$BUILD_NUMBER"
            }
        }
    }
}