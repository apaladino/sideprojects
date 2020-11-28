from django import forms
from Databases import DBS

class CompareUserForm(forms.Form):
    databases = forms.ChoiceField(label='databases', widget=forms.Select, choices=DBS, required=True)
    first_user = forms.CharField(label='first_user', max_length=100, min_length=4, required=True)
    second_user = forms.CharField(label='second_user', max_length=100, min_length=4, required=True)