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
	for vendor in lst:
		if vendor.code == data:
			items = dict()
			items['username'] = vendor.username
			items['brand'] = vendor.brand
			items['stall_no'] = vendor.stall_no
			response.append(items)
	return HttpResponse(json.dumps(response))

def register(request):
	#post request herettp
    print(request.POST.get("name"))
    return HttpResponse(data)

def restitems(request):
	response = list()
	lst = Item.objects.all()
	for item in lst:
		items = dict()
		items['vendor'] = item.vendor.username
		items['name'] = item.name
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
		items['username'] = vendor.username
		items['brand'] = vendor.brand
		items['stall_no'] = vendor.stall_no
		response.append(items)
	return HttpResponse(json.dumps(response))


