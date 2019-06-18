package com.raja.applecodingchallenge.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raja.applecodingchallenge.model.TreeNode;
import com.raja.applecodingchallenge.model.User;
import com.raja.applecodingchallenge.repository.UserRepository;
import com.raja.applecodingchallenge.service.UserService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;



@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    @Override
    public User findByUsername(String username){
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public List<String> findUsers(List<Long> idList){
        return this.userRepository.findUserIdList(idList);
    }

    @Override
    public List<User> findAllUsers(){
        return this.userRepository.findAll();
    }
    
    
//Function to calculate max sum on the longest path
	@Override
	public String calculateMaxSum(String data) {
		// TODO Auto-generated method stub
		int sum =0;
		TreeNode node = deserialize(data);
		try
		{
			sum = sumOfLongRootToLeafPathUtil(node);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return String.valueOf(sum);
	}
	 // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] arr = data.split(",");
        if (arr[0].equalsIgnoreCase("null")) {
            return null;
        }
     
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        LinkedList<TreeNode> q = new LinkedList<>();
        q.offer(root);
     
        int i = 1;
     
        while (!q.isEmpty()) {
            TreeNode h = q.poll();
            if (h != null) {
                TreeNode left = null;
                if (!arr[i].equalsIgnoreCase("null")) {
                    left = new TreeNode(Integer.parseInt(arr[i]));
                }
                h.left = left;
                q.offer(left);
                i++;
     
                TreeNode right = null;
                if (!arr[i].equalsIgnoreCase("null")) {
                    right = new TreeNode(Integer.parseInt(arr[i]));
                }
                h.right = right;
                q.offer(right);
                i++;
            }
        }
     
        return root;
    }

    static int maxLen; 
    static int maxSum; 
    
    
    // utility function to find the sum of nodes on 
    // the longest path from root to leaf node 
    static int sumOfLongRootToLeafPathUtil(TreeNode root) 
    { 
        // if tree is NULL, then sum is 0 
        if (root == null) 
            return 0; 
       
        maxSum = Integer.MIN_VALUE; 
        maxLen = 0; 
       
        // finding the maximum sum 'maxSum' for the 
        // maximum length root to leaf path 
        sumOfLongRootToLeafPath(root, 0, 0); 
       
        // required maximum sum 
        return maxSum; 
    } 
    
    // function to find the sum of nodes on the 
    // longest path from root to leaf node 
    static void sumOfLongRootToLeafPath(TreeNode root, int sum, 
                                         int len) 
    { 
        // if true, then we have traversed a 
        // root to leaf path 
        if (root == null) { 
            // update maximum length and maximum sum 
            // according to the given conditions 
            if (maxLen < len) { 
                maxLen = len; 
                maxSum = sum; 
            } else if (maxLen == len && maxSum < sum) 
                maxSum = sum; 
            return; 
        } 
          
          
        // recur for left subtree 
        sumOfLongRootToLeafPath(root.left, sum + root.val, 
                                len + 1); 
          
        sumOfLongRootToLeafPath(root.right, sum + root.val, 
                                len + 1); 
          
    } 
    //Test function 
    public  static List<List<Integer>> levelOrder(TreeNode root) {
        
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> treeQueue =  new LinkedList<>();;
        
        if(root == null)
        {
            return result;
        }
        else
        {
            treeQueue.offer(root);
        }
        
        while(!treeQueue.isEmpty())
        {
             ArrayList<Integer> levelResult = new ArrayList<Integer>();
              int size = treeQueue.size();
            for(int i =0;i<size;i++)
            { 
               
                TreeNode t = treeQueue.poll();
                levelResult.add(t.val);
                if(t.left != null)
                {
                    treeQueue.add(t.left);
                }
                if(t.right != null)
                {
                    treeQueue.add(t.right);
                }
            }
            result.add(levelResult);
        }
        return result;
    }
//    public static void main(String arg[])
//    {
//    	String test = "1,2,3,null,5,6,7,1,null,null,null,null,null,null,null";
////    	System.out.println(sumOfLongRootToLeafPathUtil(deserialize(test)));
//    	UserServiceImpl u = new UserServiceImpl();
//    	TreeNode n = u.deserializeTree(test);
//    	List<List<Integer>> result = levelOrder(n);
//    	for(List<Integer> i : result)
//    	{
//    		for(int num : i)
//    		{
//    			System.out.print(num +  " ");
//    		}
//    		System.out.println();
//    	}
//    	System.out.println(sumOfLongRootToLeafPathUtil(n));
//    }
       
}