from django.conf.urls import url

from . import views

urlpatterns = [
    url(r'^$', views.index, name='index'),
    url(r'^createuser/$', views.create_user_like_existing_user, name='createuser'),
    url(r'^transferuser/$', views.transfer_user, name='transferuser'),
    url(r'^posttransferuser/$', views.post_transfer_user, name='transferuser'),
    url(r'^compareusers/$', views.compare_users, name='compareusers'),
    url(r'^postcompareusers/$', views.post_compare_users, name='compareusers'),
    url(r'^postcreateuser.html$', views.create_user, name='postcreateuser'),
    url(r'^lookupdriver/$', views.lookup_driver, name='lookup_driver'),
    url(r'^postlookupdriver/$', views.handle_lookup_driver_submit, name='post_lookup_driver'),
    url(r'^lookupcustomer/$', views.lookup_customer, name='lookup_customer'),
    url(r'^postlookupcustomer/$', views.post_lookup_customer, name='post_lookup_customer'),
]