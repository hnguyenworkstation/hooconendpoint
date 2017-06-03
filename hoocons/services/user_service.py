from rest_framework.decorators import api_view, permission_classes
from serializers.user_serializer import UserSerializer
from rest_framework.response import Response
from rest_framework import status
from rest_framework_jwt.settings import api_settings
from models.user_model import User
from rest_framework.permissions import IsAuthenticated, AllowAny


@api_view(['POST'])
@permission_classes((AllowAny,))
def check_phone_availability(request):
    try:
        user_data = UserSerializer(data=request.data)
        if user_data.is_valid():
            phone_number = user_data['phone_number']
            user = User.objects(phone_number=phone_number).first()
            if user is None:
                return Response(status=status.HTTP_200_OK)
            else:
                return Response(status=status.HTTP_201_CREATED)
    except Exception as e:
        return {"error" : str(e)}, status.HTTP_400_BAD_REQUEST
