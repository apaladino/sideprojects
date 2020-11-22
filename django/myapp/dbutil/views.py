# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.http import HttpResponse
from django.template import loader
from dbutil.model import Constants
from dbutil.svc import CreateRequest, UserCreator, CreateUserForm, \
    LookupDriverForm, DriverLookupSvc, DriverInfo
import json

class Database:
    def __init__(self, name, title):
        self.name = name
        self.title = title

databases = [ Database("dev", "Development"), Database("staging", "Staging"),
              Database("prod", "Production"), Database("meta", "Meta")]

###     Helper Method
def gen_create_user_context():
    context = {
        'title': Constants.dbutil_title,
        'welcome_msg': Constants.create_user_welcome_msg,
        'sub_title': Constants.sub_title,
        'media_heading': Constants.create_user_header,
        'databases': databases}
    return context

def gen_lookup_driver_context():
    context = {
        'title': Constants.dbutil_title,
        'welcome_msg': Constants.lookup_driver_welcome_msg,
        'sub_title': Constants.sub_title,
        'media_heading': Constants.lookup_driver_header,
        'databases': databases}
    return context


def getRequiredPostParam(request, param):
    if not request.POST[param]:
        raise Exception("Missing %s parameter" % param)


def getCreateRequest(createUserForm):
    username = createUserForm.cleaned_data['username']
    first_name = createUserForm.cleaned_data['firstname']
    last_name = createUserForm.cleaned_data['lastname']
    primary_loc = createUserForm.cleaned_data['primaryloc']
    like_user = createUserForm.cleaned_data['likeuser']
    sid = createUserForm.cleaned_data['databases']
    return CreateRequest.CreateRequest(username, first_name, last_name, primary_loc, like_user, sid)


def handle_create_user_error(request, err_msg):
    context = gen_create_user_context()
    context['error'] = err_msg

    template = loader.get_template('dbutil/createusers.html')
    return HttpResponse(template.render(context, request))


def handle_lookup_driver_error(request, err_msg):
    context = gen_lookup_driver_context()
    context['error'] = err_msg

    template = loader.get_template('dbutil/lookupdriver.html')
    return HttpResponse(template.render(context, request))


###
###    Handler methods
###
def create_user_like_existing_user(request):
    context = gen_create_user_context()
    createUserForm = CreateUserForm.CreateUserForm()
    context['form'] = createUserForm
    template = loader.get_template('dbutil/createusers.html')
    return HttpResponse(template.render(context, request))


def index(request):
    context = {
        'title': Constants.dbutil_title,
        'welcome_msg': Constants.dbutil_welcome_msg,
        'sub_title':  Constants.sub_title,
        'media_heading': 'Main Page'}
    template = loader.get_template('dbutil/index.html')

    return HttpResponse(template.render(context, request))


def create_user(request):
    print("create_user submission received")
    print("Request method %s " % request.method)
    print("request.post %s " % request.POST )
    print("request.get %s " % request.GET )

    if request.method != "POST":
        return handle_create_user_error(request,  'Invalid request method given. Must be POST')

    try:
        # parse request params
        createUserForm = CreateUserForm.CreateUserForm(request.POST)
        
        if not createUserForm.is_valid():
            print(createUserForm.errors)
            return handle_create_user_error(request, "Invalid data submitted")

        createRequest = getCreateRequest(createUserForm)

        # create the user
        userCreator = UserCreator.UserCreator()
        results_data = userCreator.create_user_like_existing_user(createRequest)

        success_msg = "User: [%s] successfully created with same settings as existing user: [%s]" % \
                      (createRequest.username, createRequest.like_user)
        print(success_msg)

        context = gen_create_user_context()
        context['status'] = 'success';
        context['success_msg'] = success_msg
        context['user_created_msg'] = "User Successfully Created"
        context['username'] = createRequest.username
        context['first_name'] = createRequest.first_name
        context['last_name'] = createRequest.last_name
        context['primary_loc'] = createRequest.primary_loc
        context['like_user'] = createRequest.like_user
        context['newuser_roles'] = results_data['newuser.roles']
        context['newuser_locations'] = results_data['newuser.locations']
        context['likeuser_roles'] = results_data['likeuser.roles']
        context['likeuser_locations'] = results_data['likeuser.locations']

        template = loader.get_template('dbutil/usercreated.html')
        return HttpResponse(template.render(context, request))

    except Exception as e:
        print("unexpexed error occurred: %s" % e.message)
        return handle_create_user_error(request, e.message)


def lookup_driver(request):
    driverForm = LookupDriverForm.LookupDriverForm()

    context = gen_lookup_driver_context()
    context['form'] = driverForm

    template = loader.get_template('dbutil/lookupdriver.html')
    return HttpResponse(template.render(context, request))

def handle_lookup_driver_submit(request):
    print("lookup driver submission received")
    print("Request method %s " % request.method)
    print("request.post %s " % request.POST )

    if request.method != "POST":
        return handle_lookup_driver_error(request,  'Invalid request method given. Must be POST')

    try:
        lookupDriverForm = LookupDriverForm.LookupDriverForm(request.POST)

        if not lookupDriverForm.is_valid():
            print(lookupDriverForm.errors)
            return handle_create_user_error(request, "Invalid data submitted. Errors: %s" % lookupDriverForm.errors)

        # do lookup
        driverLookupSvc = DriverLookupSvc.DriverLookupSvc()
        requestInfo = lookupDriverForm.cleaned_data
        if requestInfo['license_search']:
            results = driverLookupSvc.lookup_driver_by_driver_number(requestInfo['driver_number'])
        else:
            results = driverLookupSvc.lookup_driver(requestInfo)

        context = gen_lookup_driver_context()
        #context['form'] = lookupDriverForm
        context['personData'] = results['personData']
        context['records'] = results['records']
        template = loader.get_template('dbutil/driverdetails.html')
        return HttpResponse(template.render(context, request))
    except Exception as e:
        print e
        return handle_lookup_driver_error(request, 'Unexpected error occurred: %s' % e.message)
