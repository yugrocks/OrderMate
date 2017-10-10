from . import views
from django.conf.urls import url, include
from rest_framework import routers, serializers, viewsets
from .models import *
from django.conf import settings
from django.contrib.staticfiles.urls import staticfiles_urlpatterns
from django.conf.urls.static import static

class UserSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Vendor
        fields = ('name', 'brand', 'stall_no')

urlpatterns = [
    url(r'^data/(?P<data>[a-z0-9]+)$', views.home, name='home'),
    url(r'^register$', views.register, name='register'),
    url(r'^restitems/(?P<data>[a-z0-9]+)$', views.restitems, name='rest'),
    url(r'^restvendors$', views.restvendors, name='restvendors'),
	url(r'^api-auth/', include('rest_framework.urls', namespace='rest_framework'))
]

if settings.DEBUG:
    urlpatterns += static(settings.STATIC_URL, document_root=settings.STATIC_ROOT)
    urlpatterns += static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)