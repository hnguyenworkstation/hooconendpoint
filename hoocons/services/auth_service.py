from django.views.decorators.csrf import csrf_exempt, requires_csrf_token
from rest_framework import status
from rest_framework.decorators import api_view, permission_classes, parser_classes
from rest_framework.response import Response
from rest_framework_jwt.serializers import VerifyJSONWebTokenSerializer
from rest_framework.parsers import JSONParser
from rest_framework.permissions import IsAuthenticated, AllowAny
from models.user_model import User
from rest_framework_jwt.settings import api_settings


jwt_payload_handler = api_settings.JWT_PAYLOAD_HANDLER
jwt_encode_handler = api_settings.JWT_ENCODE_HANDLER
jwt_response_payload_handler = api_settings.JWT_RESPONSE_PAYLOAD_HANDLER


def jwt_response_special_handling(response, user=None):
    # special handling for login(obtain_xxx) and register on successful jwt response
    # just add an extra info for is_necessary_user_info_filled telling the client to nav to profile page
    if user is None:
        token = response.data.get('token')
        user = __resolve_user(token)
    return response


def __resolve_user(token):
    serializer = VerifyJSONWebTokenSerializer(data={'token': token,})
    serializer.is_valid(raise_exception=True)
    return serializer.object.get('user')


@api_view(['POST'])
@permission_classes((AllowAny,))
@parser_classes((JSONParser,))
def register(request, format=None):
    if request.data is not None:
        phone_number = request.data['phone_number']
        password = request.data['password']
        user = User(phone_number=phone_number, password=password)
        user.save()

        payload = jwt_payload_handler(user)
        token = jwt_encode_handler(payload)
        response_data = jwt_response_payload_handler(token, user, request)

        return jwt_response_special_handling (
            Response(response_data, status.HTTP_201_CREATED), user=user
        )
    else:
        return Response(status=status.HTTP_400_BAD_REQUEST)