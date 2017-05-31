from django.conf.urls import url, include
from django.contrib import admin
from rest_framework import routers, serializers, viewsets
from services.auth_service import register

# Routers provide an easy way of automatically determining the URL conf.
router = routers.DefaultRouter()

# Wire up our API using automatic URL routing.
# Additionally, we include login URLs for the browsable API.
urlpatterns = [
    url(r'^admin/', include(admin.site.urls)),
    url(r'^', include(router.urls)),
    url(r'^api/register', register, name='register')
]