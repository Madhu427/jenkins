// pipeline {
//     agent any
//
//     stages {
//         stage('stage 1') {
//             steps {
//                 echo 'build'
//                 sh '''echo Now we are building the project
//                 echo build stage successfully completed'''
//             }
//         }
//         stage('stage 2') {
//             steps {
//                 echo 'test'
//
//             }
//         }
//         stage('stage 3') {
//             steps {
//                 echo 'deploy'
//             }
//         }
//     }
// }


// environment example


// pipeline {
// agent any
// environment{
// URL1 = "www.google.com"
// SSH = credentials("CENTOS")
// SSH1 = credentials("common2/ssh")
// }
// stages {
// stage(one) {
// steps {
// sh 'echo ${URL1}'
// sh 'env'
// sh 'echo ${SSH}'
// sh 'echo ${SSH1} | base64'
// }
// }
// }
// }


// Pipeline parameters

// pipeline {
//     agent any
//     parameters {
//         string(name: 'PERSON', defaultValue: '', description: 'Who should I say hello to?')
//
//         text(name: 'BIOGRAPHY', defaultValue: '', description: 'Enter some information about the person')
//
//         booleanParam(name: 'TOGGLE', defaultValue: true, description: 'Toggle this value')
//
//         choice(name: 'CHOICE', choices: ['One', 'Two', 'Three'], description: 'Pick something')
//
//         password(name: 'PASSWORD', defaultValue: 'SECRET', description: 'Enter a password')
//     }
//     stages {
//         stage('Example') {
//             steps {
//                 echo "Hello ${params.PERSON}"
//
//                 echo "Biography: ${params.BIOGRAPHY}"
//
//                 echo "Toggle: ${params.TOGGLE}"
//
//                 echo "Choice: ${params.CHOICE}"
//
//                 echo "Password: ${params.PASSWORD}"
//             }
//         }
//     }
// }



// Pipeline When condition

pipeline {
 agent any
 parameters {
 choice(name: 'CHOICE', choices: ['DEV','PRO'], description: 'Pick something')
 }

   stages {
     stage('DEV') {
      when {

                     environment name: 'ENV', value: 'DEV'
                 }
            steps {
            echo "DEV"
            }
     }
     stage('PRO') {
     when {

                          environment name: 'ENV', value: 'PRO'
                      }
                 steps {
                 echo "PRO"
                 }
          }
   }
 }
