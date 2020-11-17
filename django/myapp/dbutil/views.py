# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.http import HttpResponse
from django.shortcuts import render
from django.template import loader



dbutil_title='DBA Utilities App'
dbutil_welcome_msg='Welcome to the DBA Utilities App'
# Create your views here.
def index(request):
    context = { 'title': dbutil_title, 'welcome_msg': dbutil_welcome_msg}
    template = loader.get_template('dbutil/index.html')

    return HttpResponse(template.render(context, request))


def create_user_like_existing_user(request):
    return HttpResponse("Create user like existing user")