from django import forms

class LookupDriverForm(forms.Form):
    driver_number = forms.CharField(label='drivernumber', max_length=100, min_length=4, required=False)
    firstname = forms.CharField(label='firstname', max_length=100, min_length=4, required=False)
    lastname = forms.CharField(label='lastname', max_length=100, min_length=4, required=False)
    dob = forms.CharField(label='dob', max_length=10, min_length=4, required=False)
    license_search = forms.BooleanField(label="license_search", required=True, initial=True)
