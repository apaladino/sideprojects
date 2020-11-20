from django.conf.urls import url

from . import views

urlpatterns = [
    url(r'^$', views.index, name='index'),
    url(r'^createuser/$', views.create_user_like_existing_user, name='createuser'),
    url(r'^postcreateuser.html$', views.create_user, name='createuser'),
]