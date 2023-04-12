from django.shortcuts import render,redirect
from rest_framework import viewsets, permissions, generics, status
from rest_framework.response import Response
from rest_framework.views import APIView
from rest_framework.decorators import api_view
from rest_framework.generics import get_object_or_404
from django.core import serializers

# Create your views here.

class CommentsAPI(APIView):
    def get(self):
        return