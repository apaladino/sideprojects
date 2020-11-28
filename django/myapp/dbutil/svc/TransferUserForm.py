from django import forms
from Databases import DBS

class TransferUserForm(forms.Form):
    databases = forms.ChoiceField(label='databases', widget=forms.Select, choices=DBS, required=True)
    user = forms.CharField(label='user',widget=forms.TextInput(attrs={'placeholder': 'User to transfer'}), max_length=100, min_length=4, required=True)
    location = forms.CharField(label='location', widget=forms.TextInput(attrs={'placeholder': 'Enter Location'}), max_length=100, min_length=4, required=True)
    like_user = forms.CharField(label='like_user', widget=forms.TextInput(attrs={'placeholder': 'User to clone'}), max_length=20, min_length=4, required=False)
    mimic_user = forms.BooleanField(label="mimic_user", required=False, initial=False)
