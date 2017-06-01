from django.views.decorators.csrf import csrf_exempt, requires_csrf_token
from rest_framework import status
from rest_framework.decorators import api_view
from serializers.user_serializer import UserSerializer
from rest_framework.response import Response
from rest_framework_jwt import *


@api_view(['POST'])
@csrf_exempt
def register(request):
    user_data = UserSerializer(data=request.data)
    if user_data.is_valid():
        user_data.save()
        return Response(user_data.data, status=status.HTTP_201_CREATED)
    return Response(user_data.errors, status=status.HTTP_400_BAD_REQUEST)