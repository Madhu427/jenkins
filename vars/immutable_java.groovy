def call() {
    pipeline {

        agent {
            label "${BUILD_LABEL}"
        }

//        triggers {
//
//            pollSCM('H/2 * * * *')
//
//
//        }

        environment {
            PROG_LANG_NAME = "java"
            PROG_LANG_VERSION = "1.8"
            NEXUS = credentials('NEXUS')
        }



        stages {

            stage('label the builds') {
                steps {
                    script {
                        env.gitTag = GIT_BRANCH.split('/').last()
                        addShortText background: '', borderColor: '', color: 'red', link: '', text: "${gitTag}"
                    }
                }
            }
            stage('Compile the code') {
                steps {
                    sh 'echo compile the ${COMPONENT}  code'

                }
            }

            stage('Check the code quality') {
                steps {
                    script {
                        common.sonarQube()
                    }
                }
            }
            stage('Lint checks') {
                steps {
                    sh 'echo test cases'
                }
            }

            stage('Test the code') {
                steps {
                    sh 'echo test the code'
                }
            }
            stage('Deploy the code') {
                steps {
                    sh 'echo deploy the code'
                    sh 'env'
                }
            }
            stage('Publish Artifacts') {
                when {
                    expression { sh([returnStdout: true, script: 'echo ${GIT_BRANCH} | grep tags || true']) }
                }
                steps {
                    script {
                        common.prepareArtifacts()
                        common.publishArtifacts()
                    }
                }
            }
            stage('publish AMI') {
                when {
                    expression { sh([returnStdout: true, script: 'echo ${GIT_BRANCH} | grep tags || true']) }
                }
                steps {
                    script {
                        common.makeAMI()
                    }
                }
            }

        }

        post {
            always {
                cleanWs()
            }
        }



}