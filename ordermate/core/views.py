# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.shortcuts import render, redirect
from django.contrib.auth.models import User
from django.contrib.auth import authenticate
from django.http import HttpResponse
from django.contrib import auth
from django.contrib.auth.decorators import login_required
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

@login_required(login_url='signin')
def dashboard(request):
	#orders = Order.objects.filter(vendor.username = username)
	orders = Order.objects.filter(prepared=False)
	context = {
		"orders" : orders,
		"count": len(orders),
		}
	return render(request, 'core/dashboard.html', context)

def signup(request):
	context = {}
	return render(request,'core/index.html', context)

def signin(request):
	if request.method == 'POST':
		username = request.POST.get('username')
		password = request.POST.get('password')
		user = authenticate(username = username, password = password)
		if user is not None:
			auth.login(request, user)
			return redirect('dashboard')
		else:
			return redirect('signin')
	else:
		context = {}
		return render(request, 'core/index.html', context)

@login_required(login_url='signin')
def signout(request):
	auth.logout(request)
	# return render(request, 'core/index.html, context')
	return redirect('signin')

@login_required(login_url='signin')
def profile(request):
	context = {}
	return render(request, 'core/user.html', context)

def register(request):
	username = request.POST.get('username')
	password = request.POST.get('password')
	user = User.objects.create_user(username=username, password=password)
	
	user.save()
	user = authenticate(request, username=username, password=password)
	if user is not None:
		auth.login(request, user)
		return redirect('dashboard')
	else:
		return HttpResponse('username password incorrect')

	return HttpResponse('user registered')

def iteminsert(request):
	context = {}
	return render(request, 'core/iteminsert.html', context)

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


def receive_order(request):
	print(request.POST)
	username = request.POST.get('username')
	vendor = Vendor.objects.get(id=username.id)
	count = request.POST.get('count')
	foodname = request.POST.get('foodName')
	prepared = request.POST.get('prepared')
	print(username, count, foodname)
	order = Order.objects.create(vendor = vendor, count = count, foodname = foodname, prepared = prepared)
	order.save()
	return HttpResponse("bla bla")

def return_order_details(request):
	pass


@login_required(login_url='/signin/')
def additem(request):
	vendor = Vendor.objects.get(id=request.user.id)
	name = request.POST.get('name')
	price = request.POST.get('price')
	category = request.POST.get('category')
	typeofdish = request.POST.get('type')
	fileurl = request.POST.get('image')
	#instance = Item(name, price, category, typeofdish, fileurl)
	instance = Item.objects.create(vendor=vendor, item_name=name, price=price, category=category, food_type=typeofdish, image=fileurl)
	instance.save()
	return redirect('listitems')

def listitems(request):
	context = {
		"items": Item.objects.filter(vendor = Vendor.objects.get(id = request.user.id))
	}
	return render(request, 'core/listitems.html', context)



