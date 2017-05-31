from mongoengine import *
from datetime import *

import utils.app_constant as appconstant

SEX = ('Male', 'Female', 'Other')


class User(Document):
    phone_number = StringField(unique=True, required=True, min_length=6)
    password = StringField(min_length=8, required=True)
    date_join = DateTimeField(default=datetime.utcnow())
    user_name = StringField(unique=True, min_length=5)
    first_name = StringField()
    last_name = StringField()
    sex = StringField(max_length=6, choices=SEX, default='Male')
    last_online = DateTimeField(default=datetime.utcnow())
    display_name = StringField()
    avatar = StringField(default=appconstant.get_default_user_profile())
    birthday = DateTimeField()
    tokens = ListField(StringField(min_length=1))
    friends = ListField(ReferenceField('User'), default=[])
    friends_pending = ListField(ReferenceField('User'), default=[])
    friends_ignore = ListField(ReferenceField('User'), default=[])
    friends_request = ListField(ReferenceField('User'), default=[])
    timezone = StringField(default='GMT+7')
    location = GeoPointField(default=[-179, -85])

    def get_simple(self):
        return {
            "id": str(self.id),
            "phone_number": self.phone_number,
            "sex": self.sex,
            "display_name": self.display_name,
            "avatar": self.avatar,
            "timezone": self.timezone,
            "location": self.location,
            "last_online": str(self.last_online)
        }

    def get_simple_profile(self):
        return {
            "id": str(self.id),
            "phone_number": self.phone_number,
            "sex": self.sex,
            "display_name": self.display_name,
            "avatar": self.avatar,
            "last_online": str(self.last_online)
        }

    def get_json(self):
        return {
            "id": str(self.id),
            "phone_number": self.phone_number,
            "sex": self.sex,
            "display_name": self.display_name,
            "avatar": self.avatar,
            "timezone": self.timezone,
            "location": self.location,
            "last_online": str(self.last_online),
            "friends": [user.get_simple() for user in self.friends],
            "friends_pendding": [user.get_simple() for user in self.friends_pending],
            "friends_request": [user.get_simple() for user in self.friends_request],
            "friends_ignore": [user.get_simple() for user in self.friends_ignore]
        }