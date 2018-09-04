package com.example.abhinayvarma.task

import com.example.abhinayvarma.task.presenter.GetDataInterface
import com.example.abhinayvarma.task.presenter.PresenterLogic
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class PresenterTests {

//    private lateinit var presenter: PresenterLogic
//    private lateinit var view: GetDataInterface.View

    @Mock
    private var view: GetDataInterface.View? = null
    private var presenter: PresenterLogic? = null


    @Test
    fun testSmallTest() {
        val activity = MainActivity()
        assertNotNull("MainActivity is not available", activity)
    }

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        // Make presenter a mock while using mock repository and viewContract created above
        presenter = Mockito.spy<PresenterLogic>(PresenterLogic(this.view!!))
    }


}