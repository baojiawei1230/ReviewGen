#!/usr/bin/python
from rbtools.api.client import RBClient

client = RBClient('http://10.200.2.68/',
                  username='baojiawei',
                  password='111111')
root = client.get_root()
repos = root.get_repositories()
if repos.num_items < 1:
    raise Exception('No valid repositories.')

#print(repos.num_items)
repository = repos[12].id #No.13 repository is tnt




#create request to a repository
for index in range(1):
   review_request = root.get_review_requests().create(repository=repository)
   draft = review_request.get_or_create_draft()
   draft = draft.update(
       summary=review_request.id,target_people='baojiawei,tujinpeng',
       description='nothing',public=True)
   print review_request.id
   review = review_request.get_reviews().create()
   review.update(body_top=review_request.id, public=True,ship_it=True)



#requests = root.get_review_requests(ship_it_count=0,to_users='wangfei')
#print (requests)




client2 = RBClient('http://10.200.2.68/',
                  username='tujinpeng',
                  password='111111')
root2 = client2.get_root()



requests2 = root2.get_review_requests(ship_it_count=1,to_users='tujinpeng')

#print(len(requests2))

for index in range(len(requests2)):
   #print index
   review_request = requests2[index]
   #print review_request.id
   review = review_request.get_reviews().create()
   review.update(body_top=index, public=True,ship_it=True)

#print "Good bye!"
