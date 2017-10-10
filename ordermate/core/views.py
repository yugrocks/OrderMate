# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.shortcuts import render
from django.http import HttpResponse
from .models import *
import json

# Create your views here.

def home(request, data):#this view sends vendors with a given code
	response = list()
	lst = Vendor.objects.all()
	# for vendor in lst:
	# 	if vendor.code == data:
	# 		items = dict()
	# 		items['username'] = vendor.username
	# 		items['brand'] = vendor.brand
	# 		items['stall_no'] = vendor.stall_no
	# 		items['vendor_image'] = vendor.vendor_image.url
			# response.append(items)
	for item in Item.objects.all():
		if item.vendor.code == data:
			items = dict()
			items['username'] = item.vendor.user.username
			items['brand'] = item.vendor.brand
			items['stall_no'] = item.vendor.stall_no
			items['vendor_image'] = item.vendor.vendor_image.url
			items['name'] = item.item_name
			items['price'] = item.price
			items['image'] = item.image.url
			items['category'] = item.category
			items['food_type'] = item.food_type
			response.append(items)
	return HttpResponse(json.dumps(response))

def register(request):
	json_string = request.POST['data']
	json_data = json.loads(json_string)
	username = json_data['username']
	password = json_data['password']
	user = User.objects.create_user(username=username, password=password)
	user.save()
	return HttpResponse('user registered')

def restitems(request, code):
	response = list()
	lst = Item.objects.all()
	for item in lst:
		items = dict()
		items['vendor'] = item.vendor.user.username
		items['name'] = item.item_name
		items['price'] = item.price
		items['image'] = item.image.url
		items['category'] = item.category
		response.append(items)
	return HttpResponse(json.dumps(response))

def restvendors(request):
	response = list()
	lst = Vendor.objects.all()
	for vendor in lst:
		items = dict()
		items['username'] = vendor.user.username
		items['brand'] = vendor.brand
		items['stall_no'] = vendor.stall_no
		response.append(items)
	return HttpResponse(json.dumps(response))


