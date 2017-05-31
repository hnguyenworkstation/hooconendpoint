from rest_framework import serializers
from models.user_model import User
from rest_framework.authentication import SessionAuthentication, BasicAuthentication
from rest_framework.permissions import IsAuthenticated
from rest_framework.response import Response


class UserSerializer(serializers.Serializer, IsAuthenticated):
    id = serializers.CharField(read_only=True)
    phone_number = serializers.CharField()
    password = serializers.CharField()

    def create(self, validated_data):
        """
        Create and return a new `User` instance, given the validated data.
        """
        user = User(phone_number=validated_data.phone_number, password=validated_data.password)
        user.save()
        return user

    def update(self, instance, validated_data):
        """
        Update and return an existing `Snippet` instance, given the validated data.
        """
        instance.title = validated_data.get('title', instance.title)
        instance.save()
        return instance