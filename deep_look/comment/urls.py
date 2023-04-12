from django.urls import path
from . import views

urlpatterns = [
    path('comment/', views.CommentsAPI.as_view()),
]