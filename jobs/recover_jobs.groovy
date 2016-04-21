evaluate(new File("${WORKSPACE}/common.groovy"))

name = "recover"
repoName = 'workflow-e2e'

job(name) {
  description """
    <p>Runs the <a href="https://github.com/deis/workflow-e2e">e2e tests</a> against a <a href="https://github.com/deis/charts/tree/master/${defaults.workflowChart}">${defaults.workflowChart}</a> chart</p>
  """.stripIndent().trim()

  scm {
    git {
      remote {
        github("smothiki/${repoName}")
      }
      branch('recover')
    }
  }

  logRotator {
    daysToKeep defaults.daysToKeep
  }

  triggers {
    cron('@daily')
  }

  wrappers {
    timeout {
      absolute(30)
      failBuild()
    }
    timestamps()
    colorizeOutput 'xterm'
  }

  steps {
    shell """
      #!/usr/bin/env bash

      set -eo pipefail

      ls
    """.stripIndent().trim()
  }
}
