# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.contrib import admin
from .models import *

# Register your models here.

class VendorAdmin(admin.ModelAdmin):
	list_display= ['user', 'brand', 'code', 'stall_no']
	search_fields = ['user']

class ItemAdmin(admin.ModelAdmin):
	list_display= ['item_name', 'price', 'food_type']
	search_fields = ['item_name']


admin.site.register(Vendor, VendorAdmin)
admin.site.register(Item, ItemAdmin)