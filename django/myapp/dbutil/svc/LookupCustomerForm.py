from django import forms
from Databases import DBS

class LookupCustomerForm(forms.Form):
    databases = forms.ChoiceField(label='databases', widget=forms.Select, choices=DBS, required=True)
    customer_id = forms.CharField(label='customer_id', max_length=100, min_length=4, required=True)
