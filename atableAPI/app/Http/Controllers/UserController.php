<?php

namespace App\Http\Controllers;
use App\Models\User;
use Illuminate\Support\Str;
use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Hash;
use App\Http\Requests\Auth\LoginRequest;
use Illuminate\Support\Facades\Validator;

class UserController extends Controller
{
    //Fonction qui permet d'ajouter un utilisateur
    public function store ( Request $request)
    {
        $right = array(
            'username' => 'required|min:4|max:50|unique:App\Models\User,username',
            'name' => 'required|max:50',
            'firstname'=>'required|max:50',
            'age'=>'required|max:2',
            'email' => 'required|max:255|unique:App\Models\User,email',
            'password' => 'required|min:4'
            // 'admin'=>'required'

        );

        $validator=Validator::make($request->all(),$right);
        if ($validator->fails()){
            return response()
                        ->json(array('code'=>400,'message'=>'Be sure you respect the form'),400)
                            ->header('Content-Type','application/json');
        }
        $validated= $validator->validated();
        $validated['admin'] = 0;
        $validated['password'] = Hash::make($request->password);
        $validated['remember_token']= Str::random(10);
        User::create($validated);
        return response()
                    ->json(array('code'=> 201,'message' => 'User just add'),201)
                        ->header('Content-Type', 'application/json');
    }


    public function auth(Request $request)
    {
        if (Auth::attempt(['username' => $request->username,'password'=> $request->password])){

            return response()
                    ->json(array('message' => 'User just authenticate with success'),200)
                        ->header('Content-Type', 'application/json');

        }

        else

        {
            return response()
                    ->json(array('code'=> 400,'message' => ' Unhautorized'),400)
                        ->header('Content-Type', 'application/json');
        }
    }

    //Fonction qui liste tous les utilisateurs
    public function listUsers()
    {
        return User::all();
    }


    public function fiche_utilisateur($id)
    {
        return User::find($id);
    }
}
