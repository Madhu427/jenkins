def call() {
    pipeline {


        agent {
            label "${BUILD_LABEL}"
        }

//        triggers {
//            pollSCM('H/2 * * * *')
//        }


        stages {
            // for node js & python no need to compile thw code skip it
//            stage('Compile the code') {
//                steps{
//                    sh 'echo compile the ${COMPONENT}  code'
//                }
//            }
            stage('label the builds') {
                steps {
                     script {
                         def gitTag = GIT_BRANCH.split('/').last()
                         addShortText background: '', borderColor: '', color: 'red', link: '', text: "${gitTag}"
                     }
                }
            }
            stage('Test the code') {
                steps {
                    sh 'echo test the code'
                    script {
                        common.sonarQube()
                    }
                }
            }

            stage('Deploy the code') {
                steps {
                    sh 'echo deploy the code'
//                    sh 'env'
                }
            }


            stage('Publish Artifacts') {
               when{
                 expression { sh([returnStdout: true, script: 'echo ${GIT_BRANCH} | grep tags || true' ]) } }
                 steps {
                    script {

//                        common.publishArtifacts()
                        println 'publish artifacts'
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
 }


