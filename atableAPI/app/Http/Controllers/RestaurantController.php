<?php

namespace App\Http\Controllers;

use App\Models\Restaurant;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;

class RestaurantController extends Controller
{

    public function index()
    {
        $restaurants=Restaurant::all();
        return response()
            ->json($restaurants ,201)
            ->header('Content-Type', 'application/json');
        }

    public function store(Request $request){
        $rules=array(
            'name' => 'required',
            'description' => 'required',
            'localization' => 'required',
            'phone_number' => 'required',
            'hours' => 'required',
            'website' => 'required'
        );
        $messages=array(
                'name.required' => 'Please enter a name.',
                'description.required' => 'Please enter a description.',
                'localization.required' => 'Please enter a localization.',
                'phone_number' => 'Please enter a phone number.',
                'website' => 'Please enter a website',
                'hours' => 'Please enter a hours'
        );
        $validator=Validator::make($request->all(),$rules);
        if($validator->fails())
        {
            return response()
                ->json(array('code'=> 400,'message' => 'validation error'),400)
                ->header('Content-Type', 'application/json');
        }
        $validated= $validator->validated();
        $validated['grade']=-1;
        Restaurant::create($validated);
        return response()
        ->json(array('code'=> 201,'message' => 'restaurant created'),201)
        ->header('Content-Type', 'application/json');
    }

    public function update(Request $request, $id){
        $rules=array(
            'name' => 'required',
            'description' => 'required',
            'localization' => 'required',
            'phone_number' => 'required',
            'website'=>'required',
            'hours' => 'required'
        );
        $restaurant = Restaurant::find($id);
        if (!$restaurant){
            return response()
                ->json(array('code'=> 400,'message' => 'restaurant does not exist'),400)
                ->header('Content-Type', 'application/json');
        }
        $validator = Validator::make($request->all(), $rules);
        if($validator->fails())
        {
            return response()
                ->json(array('code'=> 400,'message' => 'validation error'),400)
                ->header('Content-Type', 'application/json');
        }
        $restaurant->update($request->all());
        return response()
        ->json(array('code'=> 201,'message' => 'restaurant updated'),201)
        ->header('Content-Type', 'application/json');
    }

    public function delete($id) {
        $restaurant = Restaurant::find($id);
        if (!$restaurant){
            return response()
            ->json(array('code'=> 400,'message' => 'restaurant does not exist'),400)
            ->header('Content-Type', 'application/json');
        }
        Restaurant::destroy($id);
        return response()
        ->json(array('code'=> 200,'message' => 'menu deleted'),200)
        ->header('Content-Type', 'application/json');
    }
}
