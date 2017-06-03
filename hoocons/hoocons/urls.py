from django.conf.urls import url, include
from django.contrib import admin
from rest_framework import routers
from rest_framework_jwt.views import obtain_jwt_token
from services.auth_service import register
from services.user_service import *

# Routers provide an easy way of automatically determining the URL conf.
router = routers.DefaultRouter()

# Wire up our API using automatic URL routing.
# Additionally, we include login URLs for the browsable API.
urlpatterns = [
    url(r'^admin/', include(admin.site.urls)),
    url(r'^', include(router.urls)),
    url(r'^token/simple', obtain_jwt_token),

    # USER LINK SERVICES
    url(r'^api/register', register, name='register'),
    url(r'^api/check/phone_number', check_phone_availability, name='check_phone_availability')
]