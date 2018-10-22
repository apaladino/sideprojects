from __future__ import unicode_literals

from django.db import models

# Create your models here.
from django.db import models
from datetime import datetime

class User(models.Model):
    id = models.AutoField(db_column='uid', primary_key=True)
    first_name = models.TextField()
    last_name = models.TextField()
    email = models.TextField(default="", null=True)
    username = models.TextField(default="", null=True)
    created_date = models.DateTimeField(default=datetime.today())

    def __unicode__(self):
        return u"%s" % self.name
