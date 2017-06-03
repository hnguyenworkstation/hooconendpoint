from rest_framework import serializers
from models.user_model import User
from rest_framework.permissions import IsAuthenticated


class UserSerializer(serializers.Serializer, IsAuthenticated):
    id = serializers.CharField(read_only=True)
    phone_number = serializers.CharField()
    password = serializers.CharField()

    def __eq__(self, other):
        """
        Checking each argument of the account fields
        """
        return self.username == other.username and self.password == other.password

    def create(self, validated_data):
        """
        Create user and getting token
        """
        user = User(phone_number=validated_data['phone_number'], password=validated_data['password'])
        user.save()
        return user

    def update(self, instance, validated_data):
        instance.title = validated_data.get('title', instance.title)
        instance.save()
        return instance
