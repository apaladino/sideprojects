# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.http import HttpResponse
from django.shortcuts import render
from django.template import loader

class Database:
    def __init__(self, name, title):
        self.name = name
        self.title = title

dbutil_title='DBA Utilities App'
dbutil_welcome_msg='Welcome to the DBA Utilities App'
create_user_welcome_msg='Create User Like Existing User'
sub_title="Andy's Automation Center"
databases = [ Database("dev", "Development"), Database("staging", "Staging"),
              Database("prod", "Production"), Database("meta", "Meta")]

# Create your views here.
def index(request):
    context = {
        'title': dbutil_title,
        'welcome_msg': dbutil_welcome_msg,
        'sub_title': sub_title,
        'media_heading': 'Main Page'}
    template = loader.get_template('dbutil/index.html')

    return HttpResponse(template.render(context, request))


def create_user_like_existing_user(request):
    context = {
        'title': dbutil_title,
        'welcome_msg': create_user_welcome_msg,
        'sub_title': sub_title,
        'media_heading': 'Create User',
        'databases' : databases}
    template = loader.get_template('dbutil/createusers.html')

    return HttpResponse(template.render(context, request))
