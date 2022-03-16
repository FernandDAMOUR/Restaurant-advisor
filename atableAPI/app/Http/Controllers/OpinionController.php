<?php

namespace App\Http\Controllers;


use App\Models\Restaurant;
use App\Models\User;
use App\Models\Opinion;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;

class OpinionController extends Controller
{
    public function index($restaurant_id)
    {
        $restaurant = Restaurant::find($restaurant_id);
        if(!$restaurant){
            return response()
                ->json(array('code'=> 400,'message' => 'restaurant does not exist'),400)
                ->header('Content-Type', 'application/json');
        }
        $opinions=Opinion::select('*')->where('restaurant_id',$restaurant_id)->get();
        return response()
        ->json($opinions,201)
        ->header('Content-Type', 'application/json');
    }

    public function store(Request $request, $restaurant_id,$user_id){
        $restaurant = Restaurant::find($restaurant_id);
        $user = User::find($user_id);
        if(!$restaurant){
            return response()
                ->json(array('code'=> 400,'message' => 'restaurant does not exist'),400)
                ->header('Content-Type', 'application/json');
        }
        if(!$user){
            return response()
                ->json(array('code'=> 400,'message' => 'user does not exist'),400)
                ->header('Content-Type', 'application/json');
        }
        $rules=array(
            'grade' => 'required',
            'description' => 'required',
        );
        $validator=Validator::make($request->all(),$rules);
        if($validator->fails())
        {
            return response()
                ->json(array('code'=> 400,'message' => 'validation error'),400)
                ->header('Content-Type', 'application/json');
        }
        $validated= $validator->validated();
        $validated['restaurant_id']=$restaurant_id;
        $validated['user_id']=$user_id;
        Opinion::create($validated);


        return response()
        ->json(array('code'=> 201,'message' => 'opinion created'),201)
        ->header('Content-Type', 'application/json');
    }

    public function update(Request $request, $restaurant_id,$user_id){
        $restaurant = Restaurant::find($restaurant_id);
        $user = User::find($user_id);
        if(!$restaurant){
            return response()
                ->json(array('code'=> 400,'message' => 'restaurant does not exist'),400)
                ->header('Content-Type', 'application/json');
        }
        if(!$user){
            return response()
                ->json(array('code'=> 400,'message' => 'user does not exist'),400)
                ->header('Content-Type', 'application/json');
        }
        $rules=array(
            'grade' => 'required',
            'description' => 'required',
        );
        $validator=Validator::make($request->all(),$rules);
        if($validator->fails())
        {
            return response()
                ->json(array('code'=> 400,'message' => 'validation error'),400)
                ->header('Content-Type', 'application/json');
        }
        Opinion::select('*')->where('restaurant_id',$restaurant_id)->where('user_id',$user_id)->update($request->all());
        return response()
        ->json(array('code'=> 201,'message' => 'opinion updated'),201)
        ->header('Content-Type', 'application/json');
    }

    public function delete($restaurant_id,$user_id) {
        $opinion_id=Opinion::select('id')->where('restaurant_id',$restaurant_id)->where('user_id',$user_id)->get();
        if (!$opinion_id){
            return response()
            ->json(array('code'=> 400,'message' => 'restaurant does not exist'),400)
            ->header('Content-Type', 'application/json');
        }
        Opinion::destroy($opinion_id);
        return response()
        ->json(array('code'=> 200,'message' => 'opinion deleted'),200)
        ->header('Content-Type', 'application/json');
    }

}
