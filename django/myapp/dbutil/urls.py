from django.conf.urls import url

from . import views

urlpatterns = [
    url(r'^$', views.index, name='index'),
    url(r'^createuser/$', views.create_user_like_existing_user, name='createuser'),
    url(r'^postcreateuser.html$', views.create_user, name='postcreateuser'),
    url(r'^lookupdriver/$', views.lookup_driver, name='lookup_driver'),
    url(r'^postlookupdriver/$', views.handle_lookup_driver_submit, name='post_lookup_driver'),
]