from django.urls import path
from . import views

urlpatterns = [
    path('predict/', views.PredictAPI.as_view()),
]