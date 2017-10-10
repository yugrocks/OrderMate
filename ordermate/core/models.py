# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models
from django.contrib.auth.models import User

class Vendor(models.Model):
    username = models.CharField(max_length=1000)
    brand = models.CharField(max_length = 1000)
    code = models.CharField(max_length = 1000, default="np")
    stall_no = models.IntegerField(null=False)

    class Meta:
        verbose_name = "Vendor"

    def __str__(self):
        return self.username

class Item(models.Model):
	vendor = models.ForeignKey(Vendor, on_delete=models.CASCADE)
	name = models.CharField(max_length=1000)
	price = models.IntegerField()
	type_choices = (
		('VEG', 'Vegetarian'),
		('NON', 'Non-Vegetarian'),
		('SOF', 'Soft-Drinks'),
		('BEV', 'Beverages'))
	category = models.CharField(max_length=3, choices=type_choices, default='VEG')
	type_food = (
		('bf', 'Breakfast'),
		('lun', 'Lunch'),
		('din', 'Dinner'))
	food_type = models.CharField(max_length=3, choices=type_food, default='bf')
	image = models.FileField()

	class Meta: 
		verbose_name = "Item"

	def __str__(self):
		return self.name




