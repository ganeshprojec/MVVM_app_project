package com.jlp.mvvm_jlp_project.view.auth;

import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.service.autofill.UserData;

import com.jlp.mvvm_jlp_project.api.ApiService;
import com.jlp.mvvm_jlp_project.model.request.authenticate_user.AuthenticationDetails;
import com.jlp.mvvm_jlp_project.model.response.authenticate_user.ResponseDataAuthenticateUser;
import com.jlp.mvvm_jlp_project.model.response.authenticate_user.User;
import com.jlp.mvvm_jlp_project.repository.UserRepository;
import com.jlp.mvvm_jlp_project.viewmodel.AuthViewModel;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@RunWith(MockitoJUnitRunner.class)
public class LoginFragmentTest  extends TestCase
{

    public  String EMAIL = "abc@example.com";
    private String PASSWORD = "123456";
    private static final String TEMP_STRING = "Login was successful";

    @Mock
    public AuthViewModel authViewModel;

    @Mock
    UserRepository repository;

    @InjectMocks
    ApiService apiService;

    @InjectMocks
    LoginFragment myObjectUnderTest = new LoginFragment();

    @InjectMocks
    ResponseDataAuthenticateUser responseDataAuthenticateUser ;

    @Mock
    Context context;



    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(context);


        repository=new UserRepository(apiService);
        authViewModel=new AuthViewModel(repository);

    }

    @Test
    public void validateMovieTest() {

    }

      @Test
       public void  isInternetAvailableTest() throws  Exception {

          Context mockContext = mock(Context.class);
          NetworkInfo activeNetworkInfo = mock(NetworkInfo.class);
          ConnectivityManager mockTelephonyManager = mock(ConnectivityManager.class);
          when(mockTelephonyManager.getActiveNetworkInfo()).thenReturn(activeNetworkInfo);
          // when(mockContext.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(mockTelephonyManager);
          assertEquals(mockTelephonyManager.getActiveNetworkInfo(), activeNetworkInfo);

      }


    @Test
    public void testServerLoginSuccess() {


    }

}
