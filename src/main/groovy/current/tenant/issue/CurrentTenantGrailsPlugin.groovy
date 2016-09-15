package current.tenant.issue

import grails.plugins.Plugin

/**
 * Created by goran on 2016-09-12.
 */
class CurrentTenantGrailsPlugin  extends Plugin {

    def grailsVersion = "3.2.0.M2 > *"
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    def title = "Current Tenant Issue"
    def author = "Göran Ehrsson"
    def authorEmail = "goran@technipelago.se"
    def description = '''\
'''
    def profiles = ['web']
}

