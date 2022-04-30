job('example1') {
    logRotator(-1, 10)
    jdk('Java 8')
    scm {
        github('jenkinsci/job-dsl-plugin', 'master')
    }
    triggers {
        githubPush()
    }
    steps {
        gradle('clean build')
    }
    publishers {
        archiveArtifacts('job-dsl-plugin/build/libs/job-dsl.hpi')
    }
}


//pipelineJob('frontend') {
//  configure { flowdefinition ->
//    flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
//      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
//        'userRemoteConfigs' {
//          'hudson.plugins.git.UserRemoteConfig' {
//            'url'('https://DevOps-Batches@dev.azure.com/DevOps-Batches/DevOps60/_git/frontend')
//          }
//        }
//        'branches' {
//          'hudson.plugins.git.BranchSpec' {
//            'name'('*/main')
//          }
//        }
//      }
//      'scriptPath'('Jenkinsfile')
//      'lightweight'(true)
//    }
//  }
//


folder('CI-Pipelines') {
    displayName('CI-Pipelines')
    description('CI-Pipelines')
}


def COMPONENTS = ["cart","catalogue","user","dispatch","shipping","payment","frontend"]

def SIZE = COMPONENTS.size -1

for(i in 0..SIZE){
    def j = COMPONENTS[i]
    pipelineJob("CI-Pipelines/${j}") {
        configure { flowdefinition ->
            flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
                'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
                    'userRemoteConfigs' {
                        'hudson.plugins.git.UserRemoteConfig' {
                            'url'("https://github.com/Madhu427/${j}.git")
                        }
                    }
                    'branches' {
                        'hudson.plugins.git.BranchSpec' {
                            'name'('*/tags/*')
                        }
                        'hudson.plugins.git.BranchSpec' {
                            'name'('*/main')
                        }
                    }
                }
                'scriptPath'('Jenkinsfile')
                'lightweight'(true)
            }
        }
    }
}

folder('Mutable') {
    displayName('Mutable')
    description('Mutable')
}

pipelineJob('Mutable/VPC') {
    configure { flowdefinition ->
        flowdefinition << delegate.'definition'(class: 'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition', plugin: 'workflow-cps') {
            'scm'(class: 'hudson.plugins.git.GitSCM', plugin: 'git') {
                'userRemoteConfigs' {
                    'hudson.plugins.git.UserRemoteConfig' {
                        'url'("https://github.com/Madhu427/terraform-vpc.git")
                    }
                }
                'branches' {
                    'hudson.plugins.git.BranchSpec' {
                        'name'('*/master')
                    }
                }
            }
            'scriptPath'('Jenkinsfile')
            'lightweight'(true)
        }
    }
}


pipelineJob('Mutable/APP-SETUP') {
    configure { flowdefinition ->
        flowdefinition << delegate.'definition'(class: 'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition', plugin: 'workflow-cps') {
            'scm'(class: 'hudson.plugins.git.GitSCM', plugin: 'git') {
                'userRemoteConfigs' {
                    'hudson.plugins.git.UserRemoteConfig' {
                        'url'("https://github.com/Madhu427/jenkins.git")
                    }
                }
                'branches' {
                    'hudson.plugins.git.BranchSpec' {
                        'name'('*/master')
                    }
                }
            }
            'scriptPath'('jenkinsfile-Mutable-Infra')
            'lightweight'(true)
        }
    }
}

pipelineJob('Mutable/ALB') {
    configure { flowdefinition ->
        flowdefinition << delegate.'definition'(class: 'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition', plugin: 'workflow-cps') {
            'scm'(class: 'hudson.plugins.git.GitSCM', plugin: 'git') {
                'userRemoteConfigs' {
                    'hudson.plugins.git.UserRemoteConfig' {
                        'url'("https://github.com/Madhu427/terraform-mutable-alb.git")
                    }
                }
                'branches' {
                    'hudson.plugins.git.BranchSpec' {
                        'name'('*/master')
                    }
                }
            }
            'scriptPath'('Jenkinsfile')
            'lightweight'(true)
        }
    }
}

pipelineJob('Mutable/DB') {
    configure { flowdefinition ->
        flowdefinition << delegate.'definition'(class: 'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition', plugin: 'workflow-cps') {
            'scm'(class: 'hudson.plugins.git.GitSCM', plugin: 'git') {
                'userRemoteConfigs' {
                    'hudson.plugins.git.UserRemoteConfig' {
                        'url'("https://github.com/Madhu427/terraform-db.git")
                    }
                }
                'branches' {
                    'hudson.plugins.git.BranchSpec' {
                        'name'('*/master')
                    }
                }
            }
            'scriptPath'('Jenkinsfile')
            'lightweight'(true)
        }
    }
}

pipelineJob('Mutable/ALL-Infra-Create') {
    configure { flowdefinition ->
        flowdefinition << delegate.'definition'(class: 'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition', plugin: 'workflow-cps') {
            'scm'(class: 'hudson.plugins.git.GitSCM', plugin: 'git') {
                'userRemoteConfigs' {
                    'hudson.plugins.git.UserRemoteConfig' {
                        'url'("https://github.com/Madhu427/jenkins.git")
                    }
                }
                'branches' {
                    'hudson.plugins.git.BranchSpec' {
                        'name'('*/master')
                    }
                }
            }
            'scriptPath'('jenkinsfile-Mutable-Infra-All-in-one-create')
            'lightweight'(true)
        }
    }
}

pipelineJob('Mutable/ALL-Infra-Destroy') {
    configure { flowdefinition ->
        flowdefinition << delegate.'definition'(class: 'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition', plugin: 'workflow-cps') {
            'scm'(class: 'hudson.plugins.git.GitSCM', plugin: 'git') {
                'userRemoteConfigs' {
                    'hudson.plugins.git.UserRemoteConfig' {
                        'url'("https://github.com/Madhu427/jenkins.git")
                    }
                }
                'branches' {
                    'hudson.plugins.git.BranchSpec' {
                        'name'('*/master')
                    }
                }
            }
            'scriptPath'('jenkinsfile-Mutable-Infra-All-in-one-destroy')
            'lightweight'(true)
        }
    }
}

////////////Immutable Setup///////
folder('Immutable') {
    displayName('Immutable')
    description('Immutable')
}

pipelineJob('Immutable/ALL-Infra-Create') {
    configure { flowdefinition ->
        flowdefinition << delegate.'definition'(class: 'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition', plugin: 'workflow-cps') {
            'scm'(class: 'hudson.plugins.git.GitSCM', plugin: 'git') {
                'userRemoteConfigs' {
                    'hudson.plugins.git.UserRemoteConfig' {
                        'url'("https://github.com/Madhu427/jenkins.git")
                    }
                }
                'branches' {
                    'hudson.plugins.git.BranchSpec' {
                        'name'('*/master')
                    }
                }
            }
            'scriptPath'('jenkinsfile-Immutable-Infra-All-in-one-create')
            'lightweight'(true)
        }
    }
}

pipelineJob('Mutable/ALL-Infra-Destroy') {
    configure { flowdefinition ->
        flowdefinition << delegate.'definition'(class: 'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition', plugin: 'workflow-cps') {
            'scm'(class: 'hudson.plugins.git.GitSCM', plugin: 'git') {
                'userRemoteConfigs' {
                    'hudson.plugins.git.UserRemoteConfig' {
                        'url'("https://github.com/Madhu427/jenkins.git")
                    }
                }
                'branches' {
                    'hudson.plugins.git.BranchSpec' {
                        'name'('*/master')
                    }
                }
            }
            'scriptPath'('jenkinsfile-Immutable-Infra-All-in-one-destroy')
            'lightweight'(true)
        }
    }
}
