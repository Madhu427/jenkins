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


pipeline {
agent any
environment{
URL1 = "www.google.com"
SSH = credentials("CENTOS")
}
stages {
stage(one) {
steps {
sh 'echo ${URL1}'
sh 'env'


}
}
}
}