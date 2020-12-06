from django import forms
from Databases import DBS
import ConfigParser
import LocationSvc

config = ConfigParser.ConfigParser()
config.read('Config.properties')

username = config.get('DatabaseSection', 'username')
password= config.get('DatabaseSection', 'password')
workDir = config.get('MainSection', 'work.dir')

locationSvc = LocationSvc.LocationSvc(username, password, workDir)
locationChoices = locationSvc.get_locations("OPAL")

class AddLocationsForm(forms.Form):
    databases = forms.ChoiceField(label='databases', widget=forms.Select, choices=DBS, required=True)
    username = forms.CharField(label='username', max_length=100, min_length=4, required=True)

    CHOICES=[('C','Choose Locations'),
             ('L','Like User')]

    options = forms.ChoiceField(choices=CHOICES, widget=forms.RadioSelect(attrs={'class' : 'Radio'}), initial=1)
    like_user = forms.CharField(label='like_user', max_length=100, min_length=4, required=False)

    locations = forms.CheckboxSelectMultiple(choices=locationChoices)