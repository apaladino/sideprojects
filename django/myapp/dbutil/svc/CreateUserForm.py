from django import forms

class CreateUserForm(forms.Form):
    DBS = (
        ('test', 'Test'),
        ('dev', 'Development'),
        ('staging', 'Staging'),
        ('meta', 'Meta'),
        ('prod', 'Production'),
    )
    databases = forms.ChoiceField(label='databases', widget=forms.Select, choices=DBS, required=True)
    username = forms.CharField(label='username', max_length=100, min_length=4, required=True)
    firstname = forms.CharField(label='firstname', max_length=100, min_length=4, required=True)
    lastname = forms.CharField(label='lastname', max_length=100, min_length=4, required=True)
    primaryloc = forms.CharField(label='primaryloc', max_length=100, min_length=4, required=True)
    likeuser = forms.CharField(label='likeuser', max_length=100, min_length=4, required=True)

