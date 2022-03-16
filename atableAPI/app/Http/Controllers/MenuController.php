<?php

namespace App\Http\Controllers;

use App\Models\Menu;
use App\Models\Restaurant;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;

class MenuController extends Controller
{
    public function index($restaurant_id)
    {
        $restaurant = Restaurant::find($restaurant_id);
        if(!$restaurant){
            return response()
                ->json(array('message' => 'restaurant does not exist'),400)
                ->header('Content-Type', 'application/json');
        }
        $menus=Menu::select('*')->where('restaurant_id',$restaurant_id)->get();
        return response()
        ->json($menus,201)
        ->header('Content-Type', 'application/json');
    }

    public function store(Request $request, $restaurant_id){
        $restaurant = Restaurant::find($restaurant_id);
        if(!$restaurant){
            return response()
                ->json(array('message' => 'restaurant does not exist'),400)
                ->header('Content-Type', 'application/json');
        }
        $rules=array(
            'name' => 'required',
            'description' => 'required',
            'price' => 'required|digits_between:1,8'
        );
        $validator=Validator::make($request->all(),$rules);
        if($validator->fails())
        {
            return response()
                ->json(array('code'=> 400,'message' => 'validation error'),400)
                ->header('Content-Type', 'application/json');
        }
        $validated= $validator->validated();

        $restaurant->menus()->create($validated);

        return response()
        ->json(array('code'=> 201,'message' => 'menu created'),201)
        ->header('Content-Type', 'application/json');
    }

    public function update(Request $request, $restaurant_id, $menu_id){
        $restaurant = Restaurant::find($restaurant_id);
        if(!$restaurant){
            return response()
                ->json(array('code'=> 400,'message' => 'restaurant does not exist'),400)
                ->header('Content-Type', 'application/json');
        }
        $menu = Menu::find($menu_id);
        if(!$menu){
            return response()
            ->json(array('code'=> 400,'message' => 'menu does not exist'),400)
            ->header('Content-Type', 'application/json');
        }
        $rules=array(
            'name' => 'required',
            'description' => 'required',
            'price' => 'required'
        );
        $validator = Validator::make($request->all(), $rules);
        if($validator->fails())
        {
            return response()
                ->json(array('code'=> 400,'message' => 'validation error'),400)
                ->header('Content-Type', 'application/json');
        }
        $validated= $validator->validated();
        Menu::select('*')->where('id',$menu_id)->update($validated);
        return response()
        ->json(array('code'=> 200,'message' => 'menu updated'),200)
        ->header('Content-Type', 'application/json');
    }


    public function delete($restaurant_id, $menu_id) {
        $restaurant = Restaurant::find($restaurant_id);
        if(!$restaurant){
            return response()
                ->json(array('code'=> 400,'message' => 'restaurant does not exist'),200)
                ->header('Content-Type', 'application/json');
        }
        $menu = Menu::find($menu_id);
        if(!$menu){
            return response()
            ->json(array('code'=> 400,'message' => 'menu does not exist'),400)
            ->header('Content-Type', 'application/json');
        }
        Menu::destroy($menu_id);
        return response()
        ->json(array('code'=> 200,'message' => 'menu deleted'),400)
        ->header('Content-Type', 'application/json');
    }
}
